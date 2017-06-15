package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;
import com.alberto.carlos.sabortropical.Entidades.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

//classe de persistencia do cliente
public class PedidoDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_VENDA= "venda";
    //Nome da tabela
    private static final String NOME_TABELA_COMPRA = "compra";
    //Nome da tabela
    private static final String NOME_TABELA_ITEM = "item";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public PedidoDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //metodo inserir fornecedor
    public Long inserirVenda(Pedido pedido) throws SQLException {

        ContentValues valuesItem = new ContentValues();
        valuesItem.put("id_produto", pedido.getItem().getProduto().getId());
        valuesItem.put("valorUnitario", pedido.getItem().getValorUnitario());
        valuesItem.put("quantidade", pedido.getItem().getQuantidade());

        Long id_item= inserirItem(valuesItem);

        ContentValues valuesPedido = new ContentValues();
        valuesPedido.put("id_cliente", pedido.getCliente().getId());
        valuesPedido.put("id_item", id_item);
        valuesPedido.put("dataVenda", pedido.getData());
        valuesPedido.put("dataVencimento", pedido.getVencimento());
        valuesPedido.put("precoTotal", pedido.getPrecoTotal());
        valuesPedido.put("desconto", pedido.getDesconto());
        valuesPedido.put("condPag", pedido.getCondPag());
        valuesPedido.put("status", pedido.getStatus());
        valuesPedido.put("observacao", pedido.getObservacao());

        Long id_pedido = inserirPedidoVenda(valuesPedido);

        return id_pedido;

    }

    //metodo inseri fornecedor no banco
    private Long inserirItem(ContentValues values) throws SQLException {
        Long id = conn.insert(NOME_TABELA_ITEM, null, values);
        return id;
    }

    //metodo inseri endereco no banco
    private Long inserirPedidoVenda(ContentValues values) throws SQLException {
        Long id = conn.insert(NOME_TABELA_VENDA, null, values);
        return id;
    }

    //metodo inserir fornecedor
    public Long inserirCompra(Pedido pedido) throws SQLException {

        ContentValues valuesItem = new ContentValues();
        valuesItem.put("id_produto", pedido.getItem().getProduto().getId());
        valuesItem.put("valorUnitario", pedido.getItem().getValorUnitario());
        valuesItem.put("quantidade", pedido.getItem().getQuantidade());

        Long id_item= inserirItem(valuesItem);

        ContentValues valuesPedido = new ContentValues();
        valuesPedido.put("id_usuario", pedido.getUsuario().getId());
        valuesPedido.put("id_item", id_item);
        valuesPedido.put("dataVenda", pedido.getData());
        valuesPedido.put("dataVencimento", pedido.getVencimento());
        valuesPedido.put("precoTotal", pedido.getPrecoTotal());
        valuesPedido.put("desconto", pedido.getDesconto());
        valuesPedido.put("condPag", pedido.getCondPag());
        valuesPedido.put("status", pedido.getStatus());
        valuesPedido.put("observacao", pedido.getObservacao());

        Long id_pedido = inserirPedidoCompra(valuesPedido);

        return id_pedido;

    }

    //metodo inseri endereco no banco
    private Long inserirPedidoCompra(ContentValues values) throws SQLException {
        Long id = conn.insert(NOME_TABELA_COMPRA, null, values);
        return id;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
