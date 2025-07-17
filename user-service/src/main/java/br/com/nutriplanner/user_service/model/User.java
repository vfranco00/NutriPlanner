package br.com.nutriplanner.user_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@Data 
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preference", nullable = false)
    private Set<String> preferences;

    public boolean isVegano() {
    return preferences != null && preferences.contains("vegano");
}

public boolean isVegetariano() {
    return preferences != null && preferences.contains("vegetariano");
}

public boolean isSemLactose() {
    return preferences != null && preferences.contains("sem_lactose");
}

public boolean isSemGluten() {
    return preferences != null && preferences.contains("sem_gluten");
}

public void setVegano(boolean vegano) {
    if (vegano) {
        preferences.add("vegano");
    } else {
        preferences.remove("vegano");
    }
    throw new UnsupportedOperationException("Unimplemented method 'setVegano'");
}

public void setSemLactose(boolean semLactose) {
    if (semLactose) {
        preferences.add("sem_lactose");
    } else {
        preferences.remove("sem_lactose");
    }
    throw new UnsupportedOperationException("Unimplemented method 'setSemLactose'");
}

public void setSemGluten(boolean semGluten) {
    if (semGluten) {
        preferences.add("sem_gluten");
    } else {
        preferences.remove("sem_gluten");
    }
    throw new UnsupportedOperationException("Unimplemented method 'setSemGluten'");
}

public void setVegetariano(boolean vegetariano) {
    if (vegetariano) {
        preferences.add("vegetariano");
    } else {
        preferences.remove("vegetariano");
    }
    throw new UnsupportedOperationException("Unimplemented method 'setVegetariano'");
}
}