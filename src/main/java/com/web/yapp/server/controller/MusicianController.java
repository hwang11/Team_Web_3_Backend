package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.*;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.service.MusicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MusicianController {
    private final MusicianService musicianService;
    private final HttpSession httpSession;



    /**
     * 뮤지션 생성 페이지 이동 컨트롤러
     * @param model
     * @return
     */
    @RequestMapping("/musicians/new")
    public String createMusicianPage(Model model){
        model.addAttribute("memberForm", new MusicianDto());
        return "createMusicianPage";
    }

    /**
     *  뮤지션 값 전체조회
     *
     * @return
     */
    @GetMapping("/musicians")
    public Map<String,Object> getMusicianAllInfo(){
        List<MusicianDto> musicianListAllInfo = new ArrayList<>();
        Map<String,Object> musicianList = new HashMap<String,Object>();
        musicianListAllInfo = musicianService.findAllMusician();
        musicianList.put("musicianListAllInfo", musicianListAllInfo);
        return musicianList;
    }
    /**
     * 뮤지션 id값 조회
     * @param id
     *
     * @return
     */
    @GetMapping("/musicians/v1/{id}")
    public Map<String,Object> getMusicianIdInfo(@PathVariable("id") Long id){
        MusicianDto musicianListIdInfo = new MusicianDto();
        Map<String,Object> musicianList = new HashMap<String,Object>();
        musicianListIdInfo = musicianService.findByIdMusician(id);
        musicianList.put("musicianListIdInfo", musicianListIdInfo);
        return musicianList;
    }

    /**
     * 뮤지션 닉네임값 조회
     * @param nickNm
     * @return
     */
    @GetMapping("/musicians/v2/{nickNm}")
    public Map<String,Object> getMusicianNickNmInfo(@PathVariable("nickNm") String nickNm){
        List<MusicianDto> musicianListNickNmInfo = new ArrayList<>();
        Map<String,Object> musicianList = new HashMap<String,Object>();
        musicianListNickNmInfo = musicianService.findByNickNmMusician(nickNm);
        musicianList.put("musicianListNickNmInfo",musicianListNickNmInfo);
        return musicianList;
    }
    /**
     * 뮤지션 생성
     * @param musicianDto
     * @param result
     * @return
     */
    @PostMapping("/musicians")
    public List<Map<String, Object>> createMusician(@Valid MusicianDto musicianDto,
                                                    BindingResult result,
                                                    @RequestBody(required = false) List<TagDto> atmoList,
                                                    @RequestBody(required = false) List<GenreDto> genreList,
                                                    @RequestBody(required = false) List<InstrumentDto> instruList,
                                                    @RequestBody(required = false) List<ThemeDto> themeList
                                 ){
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        Map<String,Object> paramMap = new HashMap<>();

//        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");
        Long musicianId = musicianService.saveRegister(musicianDto,atmoList,genreList,instruList,themeList);

        if(musicianId != null){
            paramMap.put("Success", "1");
        }else {
            paramMap.put("Success", "0");;
        }
        // 로그인정보가 Null이 아닐경우만 로직처리
        //        if(user != null){
        //
        //        }
        //
        //        // @Vaild 체크이후 올바르지 않는 값이 있다면 result에 담겨져 있음.
        //        if(result.hasErrors()){
        //            return "musicians/createMusicianPage";      // 페이지 실패처리
        //        }
        /* 파일업로드 Save + 뮤지션 id 값 */
        /* 카테고리 별 Save + 뮤지션 id 값 */
        resultMapList.add(paramMap);
        return resultMapList;
    }

    @GetMapping("/musicians/curation")
    public List<Map<String,Object>> getMusicianCurationInfo(@RequestParam(value="분위기 리스트", required = false) List<AtmosphereDto> atmoList,
                                                            @RequestParam(value="장르 리스트", required = false) List<GenreDto> genreList,
                                                            @RequestParam(value="악기 리스트", required = false) List<InstrumentDto> instruList,
                                                            @RequestParam(value="테마 리스트", required = false) List<ThemeDto> themeList){

        List<Map<String,Object>> musicianCurationMapList = new ArrayList<>();
        Map<String,Object> musicianCurationMap = new HashMap<String,Object>();
        musicianCurationMap.put("musicianCurationMapList",musicianService.findCurationMusician(atmoList,genreList,instruList,themeList));
        musicianCurationMapList.add(musicianCurationMap);
        return musicianCurationMapList;

    }



}
