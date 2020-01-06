<?php namespace App\Entities;

use ICI4\Entity;

class Barang extends Entity {
    protected $casts = [
        'nama' => 'string',
        'harga' => 'float'
    ];
}