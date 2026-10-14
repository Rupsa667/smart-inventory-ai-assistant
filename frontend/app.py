import streamlit as st
import requests
from ai_assistant import ask_ai

st.title("Smart Inventory")

# ================= PRODUCTS =================

if st.button("View Products"):

    response = requests.get("http://localhost:8888/api/products")

    if response.status_code == 200:

        products = response.json()

        search = st.text_input("Search Product")

        if search:
            products = [
                p for p in products
                if search.lower() in p["name"].lower()
            ]

        for product in products:
            st.write(
                f"ID: {product['id']} | "
                f"Name: {product['name']} | "
                f"Brand: {product['brand']} | "
                f"Price: ₹{product['price']}"
            )

    else:
        st.error("Failed to fetch products")


# ================= CATEGORIES =================

if st.button("View Categories"):

    response = requests.get("http://localhost:8888/api/categories")

    if response.status_code == 200:

        categories = response.json()

        for category in categories:
            st.write(
                f"Name: {category['name']} | "
                f"Description: {category['description']}"
            )

    else:
        st.error("Failed to fetch categories")


# ================= SUPPLIERS =================

if st.button("View Suppliers"):

    response = requests.get("http://localhost:8888/api/suppliers")

    if response.status_code == 200:

        suppliers = response.json()

        for supplier in suppliers:
            st.write(
                f"Name: {supplier['name']} | "
                f"Email: {supplier['email']} | "
                f"Phone: {supplier['phone']}"
            )

    else:
        st.error("Failed to fetch suppliers")


# ================= INVENTORY =================

if st.button("View Inventory"):

    response = requests.get("http://localhost:8888/api/inventory")

    if response.status_code == 200:

        inventory = response.json()

        for item in inventory:
            st.write(
                f"ID: {item['id']} | "
                f"Product ID: {item['productId']} | "
                f"Quantity: {item['quantity']} | "
                f"Reorder Level: {item['reorderLevel']}"
            )

    else:
        st.error("Failed to fetch inventory")


# ================= SALES =================

if st.button("View Sales"):

    response = requests.get("http://localhost:8888/api/sales")

    if response.status_code == 200:

        sales = response.json()

        for sale in sales:
            st.write(
                f"Product ID: {sale['productId']} | "
                f"Quantity: {sale['quantity']} | "
                f"Total: ₹{sale['totalAmount']} | "
                f"Date: {sale['saleDate']}"
            )

    else:
        st.error("Failed to fetch sales")



st.write("---")
st.header("AI Inventory Assistant")
question = st.text_input("Ask your question") #create for searching purpose

if st.button("AI Assistant"):
    if question:
        answer=ask_ai(question)     
        st.write(answer)
    else:
        st.warning("Please enter a question")