package vn.vnu.uet.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.model.IotResponse;
import vn.vnu.uet.iot.repository.IotRepository;
import vn.vnu.uet.iot.service.IotService;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IotServiceImpl implements IotService
{
    @Autowired
    private IotRepository iotRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<IotDto> getAll() {
        return null;
    }

    @Override
    public Iot save(String t1, String t2, String p, String h) {
        Iot iot = new Iot();
        iot.setT1(t1);
        iot.setT2(t2);
        iot.setH(h);
        iot.setP(p);
        iot.setCreatedOn(System.currentTimeMillis()/1000);
        return iotRepository.save(iot);
    }

    @Override
    public IotDto getFirst() {
        return null;
    }

    @Override
    public IotResponse getData() {

        Long time = LocalDate.now().toEpochDay()*86400;
        List<Iot> iotList = iotRepository.getByDate(time, time + 86400);
        Map<Long, List<Iot>> mapData = new HashMap<>();

        for (long i = 1; i <= 24; i++){
            mapData.put(i, new ArrayList<>());
        }

        int hour = 1;

        for (Iot iot: iotList) {
            Long t = (iot.getCreatedOn() - time)/3600 + 1;
            mapData.get(t).add(iot);
        }
        return null;
    }
}
