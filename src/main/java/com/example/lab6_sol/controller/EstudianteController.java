package com.example.lab6_sol.controller;

import com.example.lab6_sol.entity.Usuario;
import com.example.lab6_sol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listaUsuarios(Model model){
        List<Usuario> estudiantes = usuarioRepository.findByRolid(5);
        model.addAttribute("estudiantes", estudiantes);
        return "lista_usuarios";
    }

    @GetMapping("/nuevoEstudiante")
    public String nuevoEstudiante(Model model){
//        List<Curso> cursos = cursoRepository.findAll();

        Usuario usuario = new Usuario();

        model.addAttribute("usuario", usuario);
        return "nuevo_estudiante";
    }

    @PostMapping("/save")
    public String guardarNuevoEstudiante(Usuario usuario, RedirectAttributes attr) {
        if (usuario.getId() == 0) {
            attr.addFlashAttribute("msg", "Usuario creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Usuario actualizado exitosamente");
        }
        usuarioRepository.save(usuario);
        return "redirect:/estudiante/lista";
    }
}
