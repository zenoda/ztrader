package org.zenoda.ztrader.common;

import org.springframework.web.bind.annotation.*;

public class DefaultController<T extends DefaultService<S, M>, S extends DefaultEntity, M extends DefaultMapper<S>> {
    private T service;

    public DefaultController(T service) {
        this.service = service;
    }

    protected T getDefaultService() {
        return service;
    }

    @PostMapping(value = "/save")
    public S save(@RequestBody S entity) {
        return service.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    public Integer removeById(@PathVariable("id") Long id, @RequestParam("dataVersion") Integer dataVersion) {
        return service.removeById(id, dataVersion);
    }

    @GetMapping(value = "/{id}")
    public S getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }
}
