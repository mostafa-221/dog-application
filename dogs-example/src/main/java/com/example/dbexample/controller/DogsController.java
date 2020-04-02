package com.example.dbexample.controller;


import com.example.dbexample.model.DogDto;
import com.example.dbexample.model.GetString;
import com.example.dbexample.repo.Dog;
import com.example.dbexample.service.DogsService;
import com.example.dbexample.model.IdMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DogsController implements ErrorController {
    @Autowired
    private DogsService dogsService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }


    @GetMapping("/getmessage")
    public String greetingForm(Model model) {
        model.addAttribute("idMessage", new IdMessage());
        return "getmessage";
    }

    @PostMapping("/getmessage")
    public String greetingSubmit(@ModelAttribute IdMessage idMessage) {
        return "showmessage";
    }

    @GetMapping("/add_dog")
    public String addDog(Model model) {
        model.addAttribute("dog", new Dog());
        return "add_dog";
    }


//    @PostMapping("/add_dog")
//    public String addDogSubmit(Model model, @ModelAttribute Dog dog)  {
//        DogDto dogdto = null;
//        try {
//            dogdto = new DogDto(dog.getId(),dog.getName(), dog.getAge());
//        } catch (Exception e) {
//            GetString getString = new GetString("fout");
//            model.addAttribute("message", getString);
//            return "/expectederror";
//        }
//
//        dogsService.add(dogdto);
//        return "add_dog_result";
//    }


    @RequestMapping(value = "/add_dog", method = RequestMethod.POST)
    public String addDogSubmit(@ModelAttribute Dog dog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            FieldError err = result.getFieldError();
            if (err != null) {
                GetString getString = new GetString("Error in field:" + err.getField());
                model.addAttribute("message", getString);
                return "/expectederror";
            } else
                return "/error";
            }


        DogDto dogdto = null;
        try {
            dogdto = new DogDto(dog.getId(),dog.getName(), dog.getAge());
        } catch (Exception e) {
            GetString getString = new GetString("Error in field:" + e.getLocalizedMessage());
            model.addAttribute("message", getString);
            return "/expectederror";
        }
        dogsService.add(dogdto);
        return "add_dog_result";
    }

    @GetMapping("/delete_dog")
    public String deleteDog(Model model) {
        model.addAttribute("idMessage", new IdMessage());
        return "delete_dog";
    }

    @PostMapping("/delete_dog")
    public String deleteSubmit(Model model, @ModelAttribute IdMessage idMessage) {
        Dog dog = new Dog();

        dogsService.delete(idMessage.getContent());

        model.addAttribute("dog", dog);
        model.addAttribute("idMessage", idMessage);
        return "delete_dog_confirm";
    }

    @RequestMapping("/list")
    public String countsList(Model model) {
        model.addAttribute("counts", dogsService.getDogs());
        return "list";
    }

    @RequestMapping("/olddogs")
    public String countsOldList(Model model) {
        model.addAttribute("counts", dogsService.getOldDogs());
        return "list";
    }


    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @RequestMapping("/expectederror")
    public ModelAndView expectedError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
