from groq import Groq
from dotenv import load_dotenv
import requests


load_dotenv()

llm=Groq()
 

def ask_ai(question):

    products = get_products()
    suppliers = get_suppliers()
    categories = get_categories()
    sales = get_sales()
    inventory=get_inventory()
    
    prompt = f"""
    You are an inventory assistant.

    Product data:
    {products}
    Supplier data:
    {suppliers}
    Category data:
    {categories}
    Sales data:
    {sales}
    Inventory data:
    {inventory}
   
    Important:
- Match Product ID from product data with productId from inventory data.
- quantity means current stock.
- reorderLevel means minimum stock level.
- A product is low in stock when quantity <= reorderLevel.
- A product is out of stock when quantity == 0.

    User question:
    {question}

    Answer based on the product data.
    """

    response = llm.chat.completions.create(
        model="openai/gpt-oss-20b",
        messages=[
            {
                "role": "user",
                "content": prompt
            }
        ]
    )

    return response.choices[0].message.content


def get_products():
    response = requests.get(
        "http://localhost:8888/api/products"
    )

    return response.json()

def get_suppliers():
    response = requests.get(
        "http://localhost:8888/api/suppliers"
    )
    return response.json()


def get_categories():
    response = requests.get(
        "http://localhost:8888/api/categories"
    )
    return response.json()   

def get_sales():
    response = requests.get(
        "http://localhost:8888/api/sales"
    )
    return response.json()

def get_inventory():
    response = requests.get(
        "http://localhost:8888/api/inventory"
    )
    return response.json()