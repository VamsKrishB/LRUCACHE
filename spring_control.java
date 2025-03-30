import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class LRUCacheController {
    private final LRUCache cache = new LRUCache(3);

    @GetMapping("/get/{key}")
    public int get(@PathVariable int key) {
        return cache.get(key);
    }

    @PostMapping("/put")
    public String put(@RequestParam int key, @RequestParam int value) {
        cache.put(key, value);
        return "Inserted " + key + " : " + value;
    }
}
