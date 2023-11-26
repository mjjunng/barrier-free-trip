package com.example.barrierfreetrip.map.service;

import com.example.barrierfreetrip.map.domain.NearServiceInfo;

import java.util.List;

public interface MapService {
    public List<NearServiceInfo> returnNearServiceInfos(double startX, double startY,
                                                        double endX, double endY, double dis);

}
