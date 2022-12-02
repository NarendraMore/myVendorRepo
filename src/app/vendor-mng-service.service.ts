import {
  HttpClient,
  HttpClientModule,
  HttpRequest,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment.prod";
import { newProject } from "./Components/headers/navbar/project-mng/model/project";
import { Role } from "./Components/headers/navbar/role-mng/model/role";
import { User } from "./Components/headers/navbar/user-mng/model/user";
import { Vendor } from "./Components/headers/navbar/vendor-mng/model/vendor";

@Injectable({
  providedIn: "root",
})
export class VendorMngServiceService {
  constructor(private http: HttpClient) {}

  // services for user management
  addUser(userData: any) {
    console.log(userData, " data is in service now");

    return this.http.post(`${environment.url1}/vendor/user/`, userData);
  }

  getuUser() {
    return this.http.get(`${environment.url1}/vendor/user/list`);
  }

  deleteUser(id: string) {
    return this.http.delete(`${environment.url1}/vendor/user/delete/${id}`);
  }

  getUserById(id: string) {
    return this.http.get(`${environment.url1}/vendor/user/${id}`);
  }

  updateUser(data: User) {
    return this.http.patch(`${environment.url1}/vendor/user/${data.id}`, data);
  }

  // services for role management
  addRole(userData: any) {
    console.log(userData," data before adding");
    
    return this.http.post(`${environment.url1}/vendor/role`, userData);
  }

  getuRole() {
    return this.http.get(`${environment.url1}/vendor/role/getAll`);
  }

  deleteRole(id: string) {
    console.log(id, " will get deleted");

    return this.http.delete(`${environment.url1}/vendor/role/${id}`);
  }

  deleteRoles(id: string) {
    return this.http.delete(`${environment.url}/vendor/role/${id}`);
  }

  getRoleById(id: string) {
    return this.http.get(`${environment.url1}/vendor/role/${id}`);
  }

  updateRole(data: Role)  {
    return this.http.patch(
      `${environment.url1}/vendor/role/edit/${data.id}`,
      data
    );
  }

  getRoles() {
    return this.http.get(`${environment.url1}/vendor/role/getAll`);
  }

  // services for vendor management
  addVendor(userData: any) {
    return this.http.post(`${environment.url1}/vendor/newVendor`, userData);
  }

  getVendors() {
    return this.http.get(`${environment.url1}/vendor/newVendor/list`);
  }

  deleteVendor(id: string) {
    return this.http.delete(`${environment.url1}/vendor/newVendor/${id}`);
  }

  getVendorById(id: string) {
    return this.http.get(`${environment.url1}/vendor/newVendor/${id}`);
  }

  updateVendor(data: Vendor) {
    return this.http.patch(
      `${environment.url1}/vendor/newVendor/update/${data.vendorId}`,
      data
    );
  }

  // services for project management
  addClient(clientData: any) {
    return this.http.post(`${environment.url1}/vendor/project`, clientData);
  }

  updateProject(data: newProject) {
    return this.http.patch(
      `${environment.url1}/vendor/project/edit/${data.projectid}`,
      data
    );
  }

  deleteProject(id: string) {
    return this.http.delete(`${environment.url}/client/${id}`);
  }

  getProjectById(id: string) {
    return this.http.get(`${environment.url}/client/${id}`);
  }

  getClients() {
    return this.http.get(`${environment.url1}/vendor/project/getproject`);
  }

  // to save project info
  addProject(projectData: any) {
    return this.http.post(`${environment.url}/project`, projectData);
  }

  getProjects() {
    return this.http.get(`${environment.url}/project`);
  }

  // to save rfp info

  addRfp(rfpData: any) {
    return this.http.post(`${environment.url}/RFP_Details`, rfpData);
  }

  getRfpDetails() {
    return this.http.get(`${environment.url}/RFP_Details`);
  }

  // new template services
  addTemplateDetails(data: any) {
    return this.http.post(`${environment.url}/Template_Details`, data);
  }

  getTemplateDetails() {
    return this.http.get(`${environment.url}/Template_Details`);
  }

  uploadDoc(file: File,projectid:string) {
    const formdata: any = new FormData();
    formdata.append("file", file);
    formdata.append("projectid", projectid);

    // const req=this.http.post(`${environment.url1}/vendor/file/uploadDoc`,)

    const req = new HttpRequest(
      "POST",
      `${environment.url1}/vendor/project/upload`,
      formdata,
      {}
    );
    return this.http.request(req);
  }

  // id = "F22B2925-B8EA-4977-8797-F7F1C586C6B3";
  download(id:string) {
    return this.http.get(
      `${environment.url1}/vendor/project/download/${id}`,
      {
        reportProgress: true,
        responseType: "blob",
      }
    );
  }
}
