package com.artbridge.artist.domain.service;

import com.artbridge.artist.domain.model.Artist;

public interface ArtistService {
    void setUploadPendingStatus(Artist artist);
}
