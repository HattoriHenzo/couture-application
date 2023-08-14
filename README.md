# Couture-Application

This application is for Tailors and has the purpose to help them managed theirs activities
The features of the application are the followings:

- Client management
- Order management
- User management
- Employee management
- Type of dresses management

The application is an API and will be available for many clients such as: `Java`, `C#`, `Python`, `Go` and `JavaScript`

## Database Structure
- GenericPerson(Id, FirstName, LastName, Telephone, Gender)
- Employee(Id, FirstName, LastName, Telephone, Gender)
- User(Id, FirstName, LastName, Telephone, Login, Password, Category, Gender)
- Client(Id, FirstName, LastName, Telephone, Gender)
- Order(Id, Number, Date, DeliveryDate, #IdClient)
- Dress(Id, Amount, #IdOrder, #IdDressType, #IdModelType, #IdMaterialType)
- Measure(Id, Type, Value, #IdDress, #IdMeasureType)
- GenericType(Id, Name)
- DressType(Id, Name)
- ModelType(Id, Name)
- MaterialType(Id, Name, Image)
- MeasureType(Id, Name, Description)

![CoutureApp-Diagram](https://github.com/HattoriHenzo/couture-application/assets/5141285/ea5f8ad9-f0b9-4af8-a1c7-26bdb2fc454b)


## Architecture
![CoutureApp-Architecture](https://github.com/HattoriHenzo/couture-application/assets/5141285/9442418a-91f5-4673-995f-3f3ddcff1eb5)


