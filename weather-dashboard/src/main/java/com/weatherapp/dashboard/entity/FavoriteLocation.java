package com.weatherapp.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite_locations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
