package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.ContractRequestDto;
import com.web.yapp.server.controller.dto.ContractTagDto;
import com.web.yapp.server.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContractController {
    private final ContractService contractService;

    @PostMapping("/contract")
    public void createContract(ContractRequestDto contractRequestDto,
                               ContractTagDto atmo, ContractTagDto theme,
                               ContractTagDto genre,  ContractTagDto instru,
                               ContractTagDto spclNote,  List<MultipartFile> documents,
                               Long userId,  Long musicianId) throws IOException {
        contractService.createContract(contractRequestDto, atmo, theme,
                genre, instru, spclNote, documents, userId, musicianId);
    }
}