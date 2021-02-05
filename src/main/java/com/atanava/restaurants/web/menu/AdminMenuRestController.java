package com.atanava.restaurants.web.menu;

import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.service.MenuService;
import com.atanava.restaurants.util.exception.IllegalRequestDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Menu> create(@Valid @RequestBody Menu menu, @RequestParam int restaurantId) {
        checkNew(menu);
        if (menu.getDishes() == null || menu.getDishes().isEmpty()) {
            throw new IllegalRequestDataException("Menu must contain dishes");
        }
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
        }
        log.info("create menu {} for restaurant {}", menu, restaurantId);

        Menu created = service.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @RequestParam int restaurantId, @RequestParam int menuId) {
        assureIdConsistent(menu, menuId);
        if (menu.getDishes() == null || menu.getDishes().isEmpty()) {
            throw new IllegalRequestDataException("Menu must contain dishes");
        }
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
        }
        log.info("update menu {} from restaurant {}", menuId, restaurantId);
        service.update(menu, restaurantId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam int restaurantId, @RequestParam int menuId) {
        log.info("delete menu {} from restaurant {}", menuId, restaurantId);
        service.delete(menuId, restaurantId);
    }

    @GetMapping
    public Menu get(@RequestParam int restaurantId, @Nullable @RequestParam Integer menuId,
                    @Nullable @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("get menu {} from restaurant {} by date {}", menuId, restaurantId, date);
        return menuId != null ? service.get(menuId, restaurantId) : service.getByRestAndDate(restaurantId, date);
    }

    @GetMapping(value = "/all")
    public List<Menu> getAll(@Nullable @RequestParam Integer restaurantId,
                             @Nullable @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("get all menus from restaurant {} by date {}", restaurantId, date);
        return restaurantId != null ? service.getAllByRestaurant(restaurantId)
                : date != null ? service.getAllByDate(date)
                : service.getAll();
    }

}
