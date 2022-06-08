package ru.geekbrains.homework6.model;
//1. В базе данных необходимо реализовать возможность хранить информацию о покупателях (id, имя) и товарах (id, название,
//   стоимость). У каждого покупателя свой набор купленных товаров;

//2. Для обеих сущностей создаете Dao классы. Работу с SessionFactory выносите во вспомогательный класс;

//3. * Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара узнавать
//   список покупателей этого товара;

//4.** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.
//

//ВАЖНО И ОБЯЗАТЕЛЬНО! Dao классы и сервис должны являться Spring бинами (Вам нужен Spring Context без веб части).
//   Контроллеры создавать не надо.

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

//ВАЖНО! *Выкидываете *код по подготовке данных и таблиц, и делаете отдельный скрипт и формируете базу заранее.
//   Покупателей и товары в базу складываете заранее, через код этого делать не надо (лишнее усложнение). SQL-скрипт прикрепите к работе.
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "cost_product")
    @Column(name = "cost")
    private int cost;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_id")
    )
    private List<Buyer> buyersList;

    public List<Buyer> getBuyersList() {
        return buyersList;
    }

    public void setBuyersList(List<Buyer> buyersList) {
        this.buyersList = buyersList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, cost = %d ]", id, title, cost);
    }
}
