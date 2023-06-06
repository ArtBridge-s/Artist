package com.artbridge.artist.domain.service.impl;

import com.artbridge.artist.domain.model.Artist;
import com.artbridge.artist.domain.service.ArtistService;
import com.artbridge.artist.domain.standardType.Status;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Override
    public void setUploadPendingStatus(Artist artist) {
        artist.setStatus(Status.UPLOAD_PENDING);
    }
}
