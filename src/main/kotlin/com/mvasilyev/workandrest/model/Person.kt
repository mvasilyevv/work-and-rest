package com.mvasilyev.workandrest.model

import javax.persistence.*

@Entity
@Table
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "first_name")
    var firstName: String = "",

    @Column(name = "last_name")
    var lastName: String = "",

    @Column
    var password: String = "",

    @Column
    var age: Int = 0,

    @Column(unique = true, length = 30)
    var email: String = "",

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "Person_Role",
        joinColumns = [JoinColumn(name = "person_id")],
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

