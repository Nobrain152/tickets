package anyeight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ALIENWARE-PC on 2017/5/12.
 */
@Controller
public class MainController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("analyze")
    public String analyze(){
        return "analyze";
    }
    @RequestMapping("stock")
    public ModelAndView stock(String stockname){
        ModelAndView modelAndView = new ModelAndView("stock");
        modelAndView.addObject(stockname);
        System.out.println(stockname);
        return modelAndView;
    }
}
