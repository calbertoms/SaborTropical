package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.Entidades.Item;
import com.alberto.carlos.sabortropical.Entidades.Pedido;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.Entidades.Relatorio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

//classe de persistencia do cliente
public class RelatorioDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public RelatorioDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //Retorna o cursor com todos os clientes
    private Cursor getPedidoVendas(Relatorio relatorio) throws SQLException {

        String[] whereArgs = new String[] { relatorio.getDataInicio(), relatorio.getDataFim() };

        Cursor cursor =  conn.rawQuery("SELECT t1.id,t1.id_cliente,t1.id_item,t1.dataVenda,t1.dataVencimento,t1.precoTotal,t1.desconto,t1.condPag,t1.status,t1.observacao,t2.id_produto,t2.valorUnitario,t2.quantidade,t3.id_fornecedor as id_fornecedor_produto,t3.id_armazenamento as id_armazenamento_produto,t3.nome as nome_produto,t3.tipo,t3.categoria,t3.temperaturaArmazenamento,t3.temperaturaTolerancia,t3.maximoEmpilhamento,t3.dataValidade as dataValidadeProduto,t3.dataFabricacao as dataFabricacaoProduto,t3.precoCompra,t3.precoVenda FROM venda t1 INNER JOIN item t2 ON (t1.id_item = t2.id) INNER JOIN produto t3 ON (t2.id_produto = t3.id) WHERE t1.dataVenda >= ? AND t1.dataVenda <= ?", whereArgs);
        return cursor;

    }

    //Retorna o cursor com todos os clientes
    private Cursor getPedidoCompras(Relatorio relatorio) throws SQLException {

        String[] whereArgs = new String[] { relatorio.getDataInicio(), relatorio.getDataFim() };

        Cursor cursor =  conn.rawQuery("SELECT t1.id,t1.id_usuario,t1.id_item,t1.dataVenda,t1.dataVencimento,t1.precoTotal,t1.desconto,t1.condPag,t1.status,t1.observacao,t2.id_produto,t2.valorUnitario,t2.quantidade,t3.id_fornecedor as id_fornecedor_produto,t3.id_armazenamento as id_armazenamento_produto,t3.nome as nome_produto,t3.tipo,t3.categoria,t3.temperaturaArmazenamento,t3.temperaturaTolerancia,t3.maximoEmpilhamento,t3.dataValidade as dataValidadeProduto,t3.dataFabricacao as dataFabricacaoProduto,t3.precoCompra,t3.precoVenda FROM compra t1 INNER JOIN item t2 ON (t1.id_item = t2.id) INNER JOIN produto t3 ON (t2.id_produto = t3.id) WHERE t1.dataVenda >= ? AND t1.dataVenda <= ?", whereArgs);
        return cursor;

    }

    
    //metodo retorna uma lista de pedido
    public List<Pedido> listarPedidos(Relatorio relatorio) throws SQLException{

        List<Pedido> Pedidos = new ArrayList<>();

        Cursor c;

        if (relatorio.getTipo() == 0) {

             c = getPedidoCompras(relatorio);
            if (c == null) {
                return null;
            }

        }
        else  {

             c = getPedidoVendas(relatorio);
            if (c == null) {
                return null;
            }

        }

        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Item item = new Item();
            Produto produto = new Produto();
            Pedido pedido = new Pedido();
            produto.setId(c.getLong(c.getColumnIndex("id_produto")));
            produto.setNome(c.getString(c.getColumnIndex("nome_produto")));
            produto.setTipo(c.getString(c.getColumnIndex("tipo")));
            produto.setCategoria(c.getString(c.getColumnIndex("categoria")));
            produto.setTemperaturaArmazenagem(c.getInt(c.getColumnIndex("temperaturaArmazenamento")));
            produto.setTemperaturaTolerancia(c.getInt(c.getColumnIndex("temperaturaTolerancia")));
            produto.setMaximoEmpilhamento(c.getInt(c.getColumnIndex("maximoEmpilhamento")));
            produto.setDataValidade(c.getString(c.getColumnIndex("dataValidadeProduto")));
            produto.setDataFabricacao(c.getString(c.getColumnIndex("dataFabricacaoProduto")));
            produto.setPrecoCompra(c.getFloat(c.getColumnIndex("precoCompra")));
            produto.setPrecoVenda(c.getFloat(c.getColumnIndex("precoVenda")));
            item.setValorUnitario(c.getFloat(c.getColumnIndex("valorUnitario")));
            item.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            item.setProduto(produto);
            pedido.setItem(item);
            pedido.setId(c.getLong(c.getColumnIndex("id")));
            pedido.setObservacao(c.getString(c.getColumnIndex("observacao")));
            pedido.setStatus(c.getInt(c.getColumnIndex("status")));
            pedido.setDesconto(c.getFloat(c.getColumnIndex("desconto")));
            pedido.setCondPag(c.getInt(c.getColumnIndex("condPag")));
            pedido.setPrecoTotal(c.getFloat(c.getColumnIndex("precoTotal")));
            pedido.setData(c.getString(c.getColumnIndex("dataVenda")));
            pedido.setVencimento(c.getString(c.getColumnIndex("dataVencimento")));

            Pedidos.add(pedido);
        }

        return Pedidos;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
