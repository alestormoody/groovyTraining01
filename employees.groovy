import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import java.io.File

class Employees {
    // List of map objects
    List<Map<String, Object>> empList

    /**
     * Constructor of the Employees class.
     * @param jsonFile The path of the JSON file to load.
     * @throws IllegalArgumentException If the file doesn't exist, an exception is thrown with an error message.
     * @return void
     */
    Employees(String jsonFile) {
        def fileContent = new File(jsonFile)
        if (!fileContent.exists()) {
            // If the file doesn't exist, throw an exception with an error message
            throw new IllegalArgumentException("Error: missing file. Example: new Employees('file')")
        }

        def jsonSlurper = new JsonSlurper()
        def parsedJson = jsonSlurper.parse(fileContent)
        empList = parsedJson.Employees ?: []
    }

    /**
     * Returns a comma-separated string of all members of a team.
     * @param string manager the Id of manager
     * @return array teamMembers A comma-separated string of all members of a team.
    */
    public String team(String manager) {
        // Filter Employees that have the specified manager and return only their IDs
        def teamMembers = empList.findAll { employee -> employee.Manager == manager }.collect { employee -> employee.ID }
        return teamMembers.join(',')
    }

    /**
     * Returns a comma-separated string of all managers.
     * @return array managerList A comma-separated string of all managers' IDs.
    */
    public String managers() {
        // Filter Employees that are managers and return only their IDs
        def managerList = empList.findAll { employee -> employee.IsManager }.collect { employee -> employee.ID }
        return managerList.join(',')
    }

    /**
     * Returns a list of map structures representing teams and their members.
     * @return array managers A list of map structures representing teams and their members.
     */
     public List<Map<String, Object>> teams() {
        List<Map<String, Object>> output = []
        List<Map<String, Object>> managers = []

        empList.each { employee ->
            if (employee.IsManager) {
                Map<String, Object> managerNode = [:]
                managerNode.put("ID", employee.ID)
                managerNode.put("Employees", [])

                empList.each { subEmployee ->
                    if (subEmployee.Manager == employee.ID) {
                        Map<String, Object> employeeNode = [:]
                        employeeNode.put("ID", subEmployee.ID)
                        employeeNode.put("IsManager", subEmployee.IsManager)

                        managerNode.Employees.add(employeeNode)
                    }
                }
                managers.add(managerNode)
            }
        }

        output.add([Managers: managers])

        def jsonOutput = JsonOutput.prettyPrint(JsonOutput.toJson(output))
        def outputFile = new File("outputCode.json")
        outputFile.write(jsonOutput)

        return output    
    }

}

// show the list as is it 
// def emp = new Employees('input.json')
// def empList = emp.print()
// println( empList )

// test a. return a list of ID for the Emp2 manager :
def members = emp.team('Emp2')
println("a. return: ${members}")

// test b. return a list of managers :
def managers = emp.managers()
println("b. return: ${managers}")

// test c. create a file named output.json following this structure
// main node contains manager id and list of employees
// sub nodes contain all employee data except manager
def output = emp.teams()
println("c. return ${output}")