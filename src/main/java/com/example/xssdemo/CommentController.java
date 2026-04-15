package com.example.xssdemo;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    private final List<String> comments = new ArrayList<>();

    private final PolicyFactory policy =
            Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("comments", comments);
        return "index";
    }

    @PostMapping("/comment")
    public String addComment(@RequestParam String text) {

        // ❌ УЯЗВИМО:
        // comments.add(text);

        // ✅ ЗАЩИТА:
        String safeText = policy.sanitize(text);
        comments.add(safeText);

        return "redirect:/";
    }
}