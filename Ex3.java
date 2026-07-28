Employee emp = null;

switch (des.toLowerCase()) {
    case "programmer":
        emp = new Programmer(name, id, address, mail, mobile, bp);
        break;

    case "assistantprofessor":
        emp = new AssistantProfessor(name, id, address, mail, mobile, bp);
        break;

    case "associateprofessor":
        emp = new AssociateProfessor(name, id, address, mail, mobile, bp);
        break;

    case "professor":
        emp = new Professor(name, id, address, mail, mobile, bp);
        break;

    default:
        System.out.println("Invalid Designation!");
        sc.close();
        return;
}

if (emp instanceof Programmer)
    ((Programmer) emp).calculateSalary();
else if (emp instanceof AssistantProfessor)
    ((AssistantProfessor) emp).calculateSalary();
else if (emp instanceof AssociateProfessor)
    ((AssociateProfessor) emp).calculateSalary();
else if (emp instanceof Professor)
    ((Professor) emp).calculateSalary();

sc.close();
}
}
