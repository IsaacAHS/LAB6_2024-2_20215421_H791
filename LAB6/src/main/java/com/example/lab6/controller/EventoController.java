package com.example.lab6.controller;

import com.example.lab6.entity.Evento;
import com.example.lab6.repository.EventoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository){
        this.eventoRepository= eventoRepository;
    }

    @GetMapping(value={"","/"})
    public String listaEventos(Model model){
        model.addAttribute("listaEventos", eventoRepository.findAll());
        return "evento/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoEvento(@ModelAttribute("evento") Evento evento,
                              Model model){
        return "evento/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento")
                                    Evento evento, RedirectAttributes attr){
        if (evento.getId()==0){
            attr.addFlashAttribute("msg", "Evento creado exitosamente");
        }else{
            attr.addFlashAttribute("msg","Producto actualizado exitosamente");
        }
        eventoRepository.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/editar")
    public String editarEvento(@ModelAttribute("evento") Evento evento,
            Model model, @RequestParam("id") int id){
        Optional<Evento> optEvento= eventoRepository.findById(id);
        if (optEvento.isPresent()) {
            evento = optEvento.get();
            model.addAttribute("evento", evento);
            return "evento/formulario";
        } else {
            return "redirect:/eventos";
        }
    }



}
