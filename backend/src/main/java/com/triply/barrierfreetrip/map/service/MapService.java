package com.triply.barrierfreetrip.map.service;

import com.triply.barrierfreetrip.map.domain.NearServiceInfo;

import java.util.List;

public interface MapService {
    public List<NearServiceInfo> returnNearServiceInfos(double startX, double startY,
                                                        double endX, double endY, double dis);

}
