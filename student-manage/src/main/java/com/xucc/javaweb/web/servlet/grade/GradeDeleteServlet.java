package com.xucc.javaweb.web.servlet.grade;

import com.alibaba.fastjson.JSONObject;
import com.xucc.javaweb.service.GradeService;
import com.xucc.javaweb.service.StudentService;
import com.xucc.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GradeDeleteServlet extends ApplicationLoadServlet {
    
    private GradeService gradeService;
    
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        gradeService = getBean(GradeService.class);
        studentService = getBean(StudentService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String delIds = request.getParameter("delIds");
        try {
            JSONObject result = new JSONObject();
            String str[] = delIds.split(",");
            for (int i = 0; i < str.length; i++) {
                boolean f = studentService.queryStudentByGradeId(str[i]);
                if (f) {
                    result.put("errorIndex", i);
                    result.put("errorMsg", "存在学生");
                    write(response, result.toString());
                    return;
                }
            }
            int delNums = gradeService.removeGrade(delIds);
            if (delNums > 0) {
                result.put("success", "true");
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除班级失败");
            }
            write(response,result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
