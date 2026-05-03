package tn.esprit.examentv.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examentv.Services.ExamenTvServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("/Examen")
public class ExamenTvRestController {

    private final ExamenTvServiceImpl service;


}
