package dev.sapia.blog.api;

import dev.sapia.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostRestController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/viewsPerMonth")
    public ResponseEntity<?> getViewsPerMonth() {
        List<Object[]> viewsPerMonth = postRepository.findTotalViewsPerMonth();
        Map<String, Integer> viewsMap = new LinkedHashMap<>();

        for (Object[] result : viewsPerMonth) {
            String month = (String) result[0];
            Integer views = (result[1] != null) ? ((Number) result[1]).intValue() : 0; // Gestione dei valori nulli

            viewsMap.put(month, views);
        }

        return ResponseEntity.ok(viewsMap);
    }

}

