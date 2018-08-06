package com.xucc.javaweb.web.servlet.grade;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xucc.javaweb.entity.Grade;
import com.xucc.javaweb.model.PageBean;
import com.xucc.javaweb.service.GradeService;
import com.xucc.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GradeListServlet extends ApplicationLoadServlet {
    
    private GradeService gradeService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.gradeService = getBean(GradeService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String gradeName = request.getParameter("gradeName");
        if (gradeName == null) {
            gradeName = "";
        }
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        try {
            JSONObject result = new JSONObject();
            List<Grade> gradeList = gradeService.gradeList(pageBean, grade);
            JSONArray jsonArray = new JSONArray();
            for (Grade item : gradeList) {
                JSONObject object = new JSONObject();
                object.put("id", item.getId());
                object.put("gradeName", item.getGradeName());
                object.put("gradeDesc", item.getGradeDesc());
                jsonArray.add(object);
            }
            result.put("rows", jsonArray);
            int total = gradeService.gradeCount(grade);
            result.put("total", total);
            write(response, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
