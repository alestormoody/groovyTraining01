# Employees Class

This repository contains a Groovy class called "Employees" that provides functionality to manage a list of employees in a JSON format. It allows you to perform operations such as retrieving team members, listing managers, and generating a structure representing teams and their members.

## JSON File Example

The class expects a JSON file with the following format:

```json
{
  "Employees": [
    {
      "ID": "Emp1",
      "IsManager": false,
      "Manager": "Emp3"
    },
    {
      "ID": "Emp2",
      "IsManager": true
    },
    {
      "ID": "Emp3",
      "IsManager": true,
      "Manager": "Emp2"
    },
    {
      "ID": "Emp4",
      "IsManager": false,
      "Manager": "Emp2"
    }
  ]
}
```

## Usage

1. Instantiate the `Employees` class by providing the path to the JSON file.
2. Use the available methods to perform operations on the employee data.

### Constructor

The constructor of the `Employees` class loads the employee data from the specified JSON file. If the file doesn't exist, an exception is thrown with an appropriate error message.

```groovy
Employees(String jsonFile)
```

### Methods

The `Employees` class provides the following methods:

1. `team(String manager)`: Returns a comma-separated string of all members of a team, given the manager's ID.

```groovy
String team(String manager)
```

2. `managers()`: Returns a comma-separated string of all managers' IDs.

```groovy
String managers()
```

3. `teams()`: Returns a list of map structures representing teams and their members.

```groovy
List<Map<String, Object>> teams()
```

## Example Usage

The code snippet below demonstrates the usage of the `Employees` class:

```groovy
def emp = new Employees('input.json')

// Example usage of methods
def members = emp.team('Emp2')
println("Team members for Emp2: ${members}")

def managers = emp.managers()
println("List of managers: ${managers}")

def output = emp.teams()
println("Team structure: ${output}")
```

Please make sure to replace `'input.json'` with the actual path to your JSON file.

For further examples and additional information, please refer to the comments in the code.

Note: Uncomment the code section if you wish to print the employee list as is.
