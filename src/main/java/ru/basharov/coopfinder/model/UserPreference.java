package ru.basharov.coopfinder.model;

import jakarta.persistence.*;
import lombok.*;
import ru.basharov.coopfinder.model.common.Gender;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_preferences")

public class UserPreference {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "onlyHasMic", nullable = false)
    private boolean onlyHasMic;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;


}
