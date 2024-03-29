export interface Client {
  id: string;
  clientName: string;
  industry: string;
  email: string;
  firstName: string;
  lastName: string;
}

export interface Project {
  id: string;
  projectName: string;
  projectCode: string;
  partner: string;
  manager: string;
  businessUser: string;
}

export interface Rfp {
  id: string;
  rfpName: string;
  description: string;
  date: Date;
}

export interface businessOwnerForm {
  projectOwnerId: "";
  firstname: "";
  lastname: "";
  email: "";
}

export interface newProject {
  projectid: string;
  projectownerid: string;
  clientName: string;
  industry: string;
  projectName: string;
  projectCode: string;
  partnerName: string;
  managerName: string;
  businessuser: string[];
  rfpName: string;
  description: string;
  date: Date;
  businessOwner: businessOwnerForm[];
}

export interface Industry {
  industryName: string;
}

export interface BusinessUser {
  name: string;
}

export interface Manager {
  name: string;
}

export interface Partner {
  name: string;
}
