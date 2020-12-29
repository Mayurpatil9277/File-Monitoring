package com.folderController;



import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filemonitor")
public class FolderMonitorController {
	@RequestMapping(value = "/health")
	@ResponseBody
	String  index(HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();
		 try (WatchService service = FileSystems.getDefault().newWatchService()){
			   Path path = Paths.get("D:\\Monitor");
			   
			   
			   WatchKey watchKey =path.register(service, StandardWatchEventKinds.ENTRY_CREATE, 
					   StandardWatchEventKinds.ENTRY_DELETE,
					   StandardWatchEventKinds.ENTRY_MODIFY);
			   
			   do {
				  // watchKey = service.poll();
				  // Path eventDir = keyMap.get(watchKey);
				   for(WatchEvent<?> event : watchKey.pollEvents()) {
					   WatchEvent.Kind<?> kind = event.kind();
					   Path eventPath = (Path)event.context();
					   //System.out.println(eventDir + ":" + kind + ":"+ eventPath);
					   //sb.append(eventDir + ":" + kind + ":"+ eventPath);
					   System.out.println(":" + kind + ":"+ eventPath);
					   sb.append(":" + kind + ":"+ eventPath);
					  return sb.toString();
				   }
				
			} while (watchKey.reset());
			   
			   sb.toString();
			   
		   }catch (Exception e) {
			   
		   }
		return null;
	   }
	
	@GetMapping(value="/getResults")
	ModelAndView getResults(ModelAndView model) { 
		StringBuffer sb = new StringBuffer();
		
		
		File directoryPath = new File("D:\\Monitor");
		String contents[] = directoryPath.list();
	      //System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	         //System.out.println(contents[i]);
	         sb.append(contents[i]);
	      }
				
		    
		
		model.addObject("message", sb);
		model.setViewName("welcome");
		return  model;
	   }
    }


