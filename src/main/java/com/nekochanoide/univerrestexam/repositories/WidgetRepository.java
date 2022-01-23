package com.nekochanoide.univerrestexam.repositories;

import com.nekochanoide.univerrestexam.models.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WidgetRepository extends JpaRepository<Widget, Long> {
    Optional<Widget> findWidgetByName(String username);
}
