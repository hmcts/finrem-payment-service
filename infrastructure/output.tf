output "env" {
  value = "${var.env}"
}

output "idam_api_url" {
  value = "${var.idam_api_url}"
}

output "fees_api_url" {
  value = "${var.fees_api_url}"
}

output "prd_api_url" {
  value = "${var.prd_api_url}"
}

output "idam_s2s_url" {
  value = "${local.idam_s2s_url}"
}

output "test_environment" {
  value = "${local.local_env}"
}

output "vaultUri" {
  value = "${data.azurerm_key_vault.finrem_key_vault.vault_uri}"
}

output "auth_idam_client_secret" {
  value = "${data.azurerm_key_vault_secret.idam-secret.value}"
}
