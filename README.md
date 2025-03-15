# PatientDashboard
Patients Record Dashboard

Make sure you have active internet connection to connect database. Because online database is used.

Import and run local using java or spring boot project.

### Access the api documentation at:
 http://localhost:8080/v1/swagger-ui/index.html
 
## Rest Endpoints:
### 1) To add a patient. 
POST
http://localhost:8080/v1/patient/addPatient
{
    "firstName": "yuvraj",
    "middleName": "dayal",
    "lastName": "Verma",
    "age": 61,
    "address": "Pune",
    "dob": "1964-01-02",
    "gender": "Male",
    "phone": "9090909091"
}

### 2) To add a treatment for a patient. 
POST
http://localhost:8080/v1/treatment/addTreatment
{"patientId":1, "treatment":"aurvedic","treatmentDate":"2012-04-28T14:45:15"}


### 3) To add a diagnosis for a patient.
POST
http://localhost:8080/v1/diagnosis/addDiagnosis
{"patientId":1, "diagnosis":"Sugar","diagnosticDate":"2015-04-28T14:45:15"}

### 4) To adds a cohort.
http://localhost:8080/v1/cohort/addCohort
{
    "cohortName": "Sugar",
    "criteria": {
        "field": "patient.age",
        "operator": ">",
        "value": "59"
    }
}

### 5) To get patient by cohort. (dynamic filter)
GET
http://localhost:8080/v1/cohort/Hypertension/patients
{"field":"patient.age","operator":">","value":"59"}

### 6) To get patient by filter criteria.
POST
http://localhost:8080/v1/patient/findPatients
{"field":"patient.firstName","operator":"=","value":"Prashant"}

### 7) Graphql Endpoint for patient
http://localhost:8080/v1/graphiql

