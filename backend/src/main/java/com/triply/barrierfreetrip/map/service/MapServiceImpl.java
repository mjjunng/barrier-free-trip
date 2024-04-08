package com.triply.barrierfreetrip.map.service;

import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.repository.ChargerRepository;
import com.triply.barrierfreetrip.map.domain.NearServiceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService{
    private final ChargerRepository chargerRepository;
    @Override
    public List<NearServiceInfo> returnNearServiceInfos(double startX, double startY, double endX, double endY,
                                                        double dis) {
        List<Charger> nearChargersByPosFromStart = chargerRepository.findNearChargersByPos(startX, startY, dis);
        List<Charger> nearChargersByPosFromEnd = chargerRepository.findNearChargersByPos(endX, endY, dis);

        // 출발지 근처 dis m 내에 위치하는 충전기
        List<NearServiceInfo> result = nearChargersByPosFromStart.stream()
                .map(c -> new NearServiceInfo(0, c.getTitle(), c.getMapx(), c.getMapy()))
                .collect(Collectors.toList());

        // 도착지 근처 dis m 내에 위치하는 충전기
        result.addAll(nearChargersByPosFromEnd.stream()
                .map(c -> new NearServiceInfo(0, c.getTitle(), c.getMapx(), c.getMapy()))
                .collect(Collectors.toList()));

        return result;
    }
}
