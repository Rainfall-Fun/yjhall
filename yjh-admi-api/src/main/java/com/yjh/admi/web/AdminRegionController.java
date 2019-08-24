package com.yjh.admi.web;

import com.yjh.admi.vo.RegionVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.AllYwqy;
import com.yjh.db.service.BusinessRegionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/region")
@Validated
public class AdminRegionController {
    private final Log logger = LogFactory.getLog(AdminRegionController.class);

    @Autowired
    private BusinessRegionService regionService;

//    @GetMapping("/clist")
//    public Object clist(@NotNull Integer id) {
//        List<AllYwqy> regionList = regionService.queryByPid(id);
//        return ResponseUtil.okList(regionList);
//    }

    @GetMapping("/list")
    public Object list() {
        List<RegionVo> regionVoList = new ArrayList<>();

        List<AllYwqy> provinceList = regionService.getAllL1Area();
        for (AllYwqy province : provinceList) {
            RegionVo provinceVO = new RegionVo();
            provinceVO.setId(province.getYwqybm());
            provinceVO.setName(province.getQymc());
            provinceVO.setCode(province.getBz());
            provinceVO.setType(province.getQydj());

            List<AllYwqy> cityList = regionService.getAllL2AreaBySuperiorArea(province.getYwqybm());
            List<RegionVo> cityVOList = new ArrayList<>();
            for (AllYwqy city : cityList) {
                RegionVo cityVO = new RegionVo();
                cityVO.setId(city.getYwqybm());
                cityVO.setName(city.getQymc());
                cityVO.setCode(city.getBz());
                cityVO.setType(city.getQydj());

                List<AllYwqy> areaList = regionService.getAllL3AreaBySuperiorArea(city.getYwqybm());
                List<RegionVo> areaVOList = new ArrayList<>();
                for (AllYwqy area : areaList) {
                    RegionVo areaVO = new RegionVo();
                    areaVO.setId(area.getYwqybm());
                    areaVO.setName(area.getQymc());
                    areaVO.setCode(area.getBz());
                    areaVO.setType(area.getQydj());
                    areaVOList.add(areaVO);
                }

                cityVO.setChildren(areaVOList);
                cityVOList.add(cityVO);
            }
            provinceVO.setChildren(cityVOList);
            regionVoList.add(provinceVO);
        }

        return ResponseUtil.okList(regionVoList);
    }
}
