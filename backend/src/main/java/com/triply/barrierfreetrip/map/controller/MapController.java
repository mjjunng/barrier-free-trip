package com.triply.barrierfreetrip.map.controller;


import com.triply.barrierfreetrip.map.domain.NearServiceInfo;
import com.triply.barrierfreetrip.map.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MapController {
    private final MapService mapService;

    @GetMapping("/map/near-service/{startX}/{startY}/{endX}/{endY}")
    public ResponseEntity returnNearService(@PathVariable("startX") double startX,
                                      @PathVariable("startY") double startY,
                                      @PathVariable("endX") double endX,
                                      @PathVariable("endY") double endY) {

        int dis = 3;
        List<NearServiceInfo> result = mapService.returnNearServiceInfos(startX, startY, endX, endY, dis);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
