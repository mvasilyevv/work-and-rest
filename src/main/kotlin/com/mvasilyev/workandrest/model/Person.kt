package com.mvasilyev.workandrest.model

import javax.persistence.*

@Entity
@Table
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column
    var password: String,

    @Column
    var age: Int,

    @Column(unique = true, length = 30)
    var email: String,

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "User_Role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Set<Role> = HashSet(),

    @Embedded
    var parameters: Parameters = Parameters()
) {
    @Embeddable
    data class Parameters(
        var height: Double = 0.0,
        var weight: Double = 0.0
    )
}

