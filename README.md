# Invoice System API

## Overview
This is a simple invoice system that allows you to create, pay, and process invoices.

## API Endpoints

- **Create Invoice**
  - `POST /invoices`
    - Request Body: 
      ```json
      {
        "amount": 199.99,
        "due_date": "2021-09-11"
      }
      ```
    - Response:
      ```json
      {
        "id": "1234"
      }
      ```

- **Get All Invoices**
  - `GET /invoices`
    - Response:
      ```json
      [
        {
          "id": "1234",
          "amount": 199.99,
          "paid_amount": 0,
          "due_date": "2021-09-11",
          "status": "pending"
        }
      ]
      ```

- **Pay Invoice**
  - `POST /invoices/<id>/payments`
    - Request Body:
      ```json
      {
        "amount": 159.99
      }
      ```

- **Process Overdue Invoices**
  - `POST /invoices/process-overdue`
    - Request Body:
      ```json
      {
        "late_fee": 10.5,
        "overdue_days": 10
      }
      ```

## Running the Application

1. Build the project with Maven:
   ```bash
   mvn clean package
