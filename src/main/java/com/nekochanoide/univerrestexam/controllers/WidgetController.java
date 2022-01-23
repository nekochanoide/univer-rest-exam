package com.nekochanoide.univerrestexam.controllers;

import com.nekochanoide.univerrestexam.exceptions.WidgetNotFoundException;
import com.nekochanoide.univerrestexam.models.Widget;
import com.nekochanoide.univerrestexam.models.WidgetWrapper;
import com.nekochanoide.univerrestexam.repositories.WidgetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class WidgetController {
    public static final String WeatherWidget = "today_weather";
    public static final String SeriesWidget = "series_recommendation";
    public static final String CongratulationWidget = "congratulation_adviser";
    public static final String WidgetsRoute = "/widgets";
    public static final String WidgetByNameRoute = WidgetsRoute + "/{name}";
    private final WidgetRepository widgetRepository;

    public WidgetController(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    @GetMapping("/" + WeatherWidget)
    WidgetWrapper todayWeatherWidget() {
        return new WidgetWrapper(widgetRepository.findWidgetByName(WeatherWidget).get());
    }

    @GetMapping("/" + SeriesWidget)
    WidgetWrapper seriesRecommendationWidget() {
        return new WidgetWrapper(widgetRepository.findWidgetByName(SeriesWidget).get());
    }

    @GetMapping("/" + CongratulationWidget)
    WidgetWrapper congratulationAdviserWidget() {
        return new WidgetWrapper(widgetRepository.findWidgetByName(CongratulationWidget).get());
    }

    @GetMapping(WidgetsRoute)
    List<Widget> all() {
        return widgetRepository.findAll();
    }

    @PostMapping(WidgetsRoute)
    Widget newEmployee(@RequestBody Widget newWidget) {
        return widgetRepository.save(newWidget);
    }

    @GetMapping(WidgetByNameRoute)
    Widget one(@PathVariable String name) {
        return widgetRepository.findWidgetByName(name).stream().findFirst().orElseThrow(() -> new WidgetNotFoundException(name));
    }

    @PutMapping(WidgetByNameRoute)
    Widget replaceEmployee(@RequestBody Widget newWidget, @PathVariable String name) {
        return widgetRepository.findWidgetByName(name)
                .map(widget -> {
                    widget.setName(newWidget.getName());
                    widget.setValue1(newWidget.getValue1());
                    widget.setValue2(newWidget.getValue2());
                    return widgetRepository.save(widget);
                })
                .orElseGet(() -> {
                    return widgetRepository.save(newWidget);
                });
    }

    @DeleteMapping(WidgetByNameRoute)
    void deleteEmployee(@PathVariable String name) {
        if (Objects.equals(name, WeatherWidget) || Objects.equals(name, SeriesWidget) || Objects.equals(name, CongratulationWidget)) {
            throw new WidgetNotFoundException(name);
        }
        Widget widget = widgetRepository.findWidgetByName(name).stream().findFirst().orElseThrow(() -> new WidgetNotFoundException(name));
        widgetRepository.delete(widget);
    }
}
