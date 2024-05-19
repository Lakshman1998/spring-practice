package org.springpractice.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResourceDetails {
    private byte[] bytes;
    private int rangeStart;
    private int rangeEnd;
    private int chunkSize;
    private boolean isFullVideo;
    private String contentType;
    private String unit;
}
