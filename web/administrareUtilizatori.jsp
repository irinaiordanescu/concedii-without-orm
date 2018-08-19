<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    if (((String) session.getAttribute("id") == null) || ((String) session.getAttribute("este_admin") == "1")) {
        response.setStatus(HttpServletResponse.SC_FOUND);//302
        response.setHeader("Location", "http://localhost:8080/Concedii/paginaLogin.html/");
    }
%>
<html>
    <head>
        <title>Administrare Utilizatori</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href='css/fabmin.css'>-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/administrareUtilizatori.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row" style='margin-bottom: 30px'>
                        <div class="col-sm-6">
                            <h2>Administrare Utilizatori</h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Adauga angajat</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Tip Angajat</th>
                            <th>Prioritate</th>
                            <th>Departament</th>
                            <th>Admin</th>
                            <th>Actiuni</th>
                        </tr>
                    </thead>
                    <tbody id="angajati">

                    </tbody>
                </table>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Adauga angajat</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" id="adaugaUsernameUtilizator" class="form-control" required="">
                            </div>
                            <div class="form-group">
                                <label>Parola</label>
                                <input type="password" id="adaugaParolaUtilizator" class="form-control" required="">
                            </div>

                            <div class="form-group">
                                <label>Verifica Parola</label>
                                <input type="password" id="adaugaVerificareParolaUtilizator" class="form-control" required="">
                            </div>
                            <div class="form-group">
                                <label>Prioritate</label>
                                <input type="number" min="1" id="adaugaPrioritateUtilizator" class="form-control" required="">
                            </div>
                             <div class="form-group">
                            <select id="tipangajat" class="form-control" style="margin-top:5px;"  required="">
                                <option disabled selected hidden>Tip angajat</option>
                                <option value="1">sef nivel 1</option>
                                <option value="2">sef nivel 2</option>
                                <option value="3">angajat junior</option>
                                <option value="4">angajat senior</option>
                            </select>
                             </div>
                             <div class="form-group">
                            <select id="departament" class="form-control" style="margin-top:5px;"  required="">
                                <option disabled selected hidden>Departament</option>
                                <option value="1">HR</option>
                                <option value="2">IT</option>
                                <option value="3">MARKETING</option>
                            </select>
                             </div>
                            <div class="form-group">
                                <label>Admin</label>
                                <input type="checkbox" id="adaugaAdminUtilizator" class="checkbox-inline" required="">
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add" onclick="adaugaUtilizator()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Editeaza angajat</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" class="form-control" required="">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="text" class="form-control" required="">
                            </div>
                            <div class="form-group">
                                <label>Prioritate</label>
                                <input type="text" class="form-control" required="">
                            </div>
                            <div class="form-group">
                                <label>Admin</label>
                                <input type="checkbox" class="checkbox-inline">
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
     

    </body>

</html>
