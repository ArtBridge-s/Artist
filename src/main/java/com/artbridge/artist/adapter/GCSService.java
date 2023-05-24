package com.artbridge.artist.adapter;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface GCSService {
    String uploadImageToGCS(MultipartFile imageFile) throws IOException;
}
