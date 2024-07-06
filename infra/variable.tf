variable "aws_access_key" {
  description = "AWS Access Key"
  type        = string
}

variable "aws_secret_key" {
  description = "AWS Secret Key"
  type        = string
}

variable "aws_region" {
  description = "AWS Region"
  type        = string
  default     = "us-east-2"
}

variable "aws_endpoint_mysql" {
  description = "Endpoint Mysql"
  type        = string
  default     = "https://mysql.us-east-2.amazonaws.com"
}
