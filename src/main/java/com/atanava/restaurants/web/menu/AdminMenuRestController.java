package com.atanava.restaurants.web.menu;

import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminMenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/menus";

    private final MenuService service;

    public AdminMenuRestController(MenuService service) {
        this.service = service;
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu, @PathVariable int restaurantId) {
        checkNew(menu);
        log.info("create dish {} for restaurant {}", menu, restaurantId);

        Menu created = service.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(menu, id);
        log.info("update menu {} from restaurant {}", id, restaurantId);
        service.update(menu, restaurantId);
    }

    @DeleteMapping(value = "/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete menu {} from restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/{id}")
    public Menu get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get menu {} from restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/by-date")
    public Menu getByRestAndDate(@RequestBody LocalDate date, @PathVariable int restaurantId) {
        log.info("get menu from restaurant {} by date {}", restaurantId, date);
        return service.getByRestAndDate(restaurantId, date);
    }

    @GetMapping
    public List<Menu> getAll() {
        log.info("get all menus");
        return service.getAll();
    }

    @GetMapping(value = "/{restaurantId}")
    public List<Menu> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("get all menus from restaurant {}", restaurantId);
        return service.getAllByRestaurant(restaurantId);
    }

    @GetMapping(value = "/by-date")
    public List<Menu> getAllByDate(@RequestBody LocalDate date) {
        log.info("get all menus by date {}", date);
        return service.getAllByDate(date);
    }
}
