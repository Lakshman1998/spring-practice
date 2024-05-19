package org.springpractice.servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springpractice.models.ResourceDetails;

import java.io.BufferedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ResourceServlet.class.getCanonicalName());
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT_RANGE = "Accept-Ranges";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_RANGE = "Content-Range";
    private static final String RANGE = "Range";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String range = request.getHeader(RANGE);
        int start = 0;
        int end = Integer.MAX_VALUE;
        if(range != null && !range.isEmpty()) {
            String[] startAndEnd = range.replace("byte=", "").split("-");
            start = Integer.parseInt(startAndEnd[0]);
            if(startAndEnd.length == 2) {
                end = Integer.parseInt(startAndEnd[1]);
            }
        }
        try {
            ResourceDetails resourceDetails = getChunksWithStartAndEnd(0L, start, end);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(resourceDetails.getBytes());
            response.setHeader(CONTENT_TYPE, resourceDetails.getContentType());
            response.setHeader(ACCEPT_RANGE, resourceDetails.getUnit());
            response.setHeader(CONTENT_LENGTH, String.valueOf(resourceDetails.getChunkSize()));
            response.setHeader(CONTENT_RANGE, String.format("bytes %s-%s/%s", resourceDetails.getRangeStart(), resourceDetails.getRangeEnd(), resourceDetails.getChunkSize()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResourceDetails getChunksWithStartAndEnd(Long videoId, int start, int end) throws Exception {
        ClassPathResource resource = new ClassPathResource("shinchan.mp4");
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(resource.getInputStream())) {
            boolean isFullVideo = false;
            if(end == Integer.MAX_VALUE) {
                end = (int) resource.getFile().length();
                isFullVideo = true;
            }
            int chunkSize = end - start + 1;
            byte[] buffer = new byte[chunkSize];
            LOGGER.log(Level.INFO, String.format("Video range from %s and %s", start, end));
            bufferedInputStream.read(buffer, start, end);
            LOGGER.log(Level.INFO, String.format("Completed Reading video files chunk size of %s", buffer.length));
            ResourceDetails resourceDetails = ResourceDetails.builder()
                    .bytes(buffer).chunkSize(chunkSize)
                    .rangeStart(start).rangeEnd(end)
                    .unit("bytes").contentType("video/mp4")
                    .isFullVideo(isFullVideo)
                    .build();
            bufferedInputStream.close();
            return resourceDetails;
        }
    }
}
