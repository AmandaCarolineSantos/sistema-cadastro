package br.com.sistemacadastro.utils;

import java.io.Serializable;

public interface Model<ID extends Serializable> extends Serializable {

    ID getId();
}
