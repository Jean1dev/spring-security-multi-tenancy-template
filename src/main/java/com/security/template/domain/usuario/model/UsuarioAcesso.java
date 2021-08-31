package com.security.template.domain.usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.template.domain.tenant.model.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario_acesso")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "login é obrigatorio")
    private String login;

    @NotEmpty(message = "senha é obrigatorio")
    private String password;

    private Boolean contaConfirmada = false;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant", referencedColumnName = "id")
    private Tenant tenant;
}
