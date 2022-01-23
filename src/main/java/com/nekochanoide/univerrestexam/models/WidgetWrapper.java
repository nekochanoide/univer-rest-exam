package com.nekochanoide.univerrestexam.models;

// В примере данные в JSON были обернуты в объект с полем `widget`.
public class WidgetWrapper {
    private Widget widget;

    public WidgetWrapper(Widget widget) {
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
