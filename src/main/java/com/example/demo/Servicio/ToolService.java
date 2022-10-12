/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Repositorio.ToolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Tool;
/**
 *
 * @author USUARIO
 */

@Service

public class ToolService {
      @Autowired
    private ToolRepository toolRepository;
    
    public List<Tool> getAll(){
        return toolRepository.getAll();
    }
    
    public Optional<Tool> getTool(int id){
        return toolRepository.getTool(id);
    }
    
    public Tool save (Tool tool){
        if (tool.getId() == null){
            return toolRepository.save(tool);
        } else {
            Optional<Tool> tool1 = toolRepository.getTool(tool.getId());
            if(tool1.isEmpty()){
                return toolRepository.save(tool);
            } else {
                return tool;
            }
        }
    }
    
       public Tool update(Tool tool){
        if(tool.getId()!=null){
            Optional<Tool> e= toolRepository.getTool(tool.getId());
            if(!e.isEmpty()){
                if(tool.getName()!=null){
                    e.get().setName(tool.getName());
                }
                if(tool.getBrand()!=null){
                    e.get().setBrand(tool.getBrand());
                }
                if(tool.getYear()!=null){
                    e.get().setYear(tool.getYear());
                }
                if(tool.getDescription()!=null){
                    e.get().setDescription(tool.getDescription());
                }
                if(tool.getCategory()!=null){
                    e.get().setCategory(tool.getCategory());
                }
                toolRepository.save(e.get());
                return e.get();
            }else{
                return tool;
            }
        }else{
            return tool;
        }
    }
    
    
         public boolean deleteTool(int id){
        Boolean d = getTool(id).map(tool -> {
            toolRepository.delete(tool);
            return true;
        }).orElse(false);
        return d;  
    }
}
