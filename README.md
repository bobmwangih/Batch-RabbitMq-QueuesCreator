# Batch-RabbitMq-QueuesCreator
This is a simple java program that can be used to create numerous number of queues in rabbitMq server from a text file .
It is very useful when you want to spin up a rabbitMq server in a different env and want to save the time you would spend creating queues, exchanges and their routing keys one by one.

## Technology Stack 🛠

| SpringBoot 
| RabbitMq

## Requirements

1. Java - 1.8 +

2. Maven 

3. RabbitMq Server running 

## Steps to Setup

**1. Clone the application**

`git clone [https://github.com/bobmwangih/Batch-RabbitMq-QueuesCreator.git]`

**2. Create a text file to hold the queue names to be created, each queue should be on its own line 

`D:/queues.txt `

**3. Build the jar and run it.

## AND cheers!!

# Note, i did not create the exchanges, and ofcourse i did not bind the queues to the exchange. but very minimal modification would be required to do that if you want to.
